package com.ably.service;

import com.ably.dto.product.request.ProductSaveRequest;
import com.ably.dto.user.type.RoleType;
import com.ably.entity.Member;
import com.ably.entity.Product;
import com.ably.entity.Wish;
import com.ably.entity.WishDraw;
import com.ably.repository.MemberRepository;
import com.ably.repository.ProductRepository;
import com.ably.repository.WishDrawRespository;
import com.ably.repository.WishRepository;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DummyDataService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final WishDrawRespository wishDrawRespository;
    private final WishRepository wishRepository;

    @PostConstruct
    public void initDataSet(){
        var products = productRepository.saveAll(
                getListByCsv("dummy_product.csv").stream()
                        .map(data -> Product.createProduct(
                                ProductSaveRequest.builder()
                                        .name(data[0])
                                        .thumbnail(data[1])
                                        .price(Long.valueOf(data[2]))
                                        .build()
                        )).collect(Collectors.toList())
        );

        for (int i = 0; i < 100; i++) {
            var member = memberRepository.save(Member.builder()
                    .email(String.format("carrey%s@naver.com", i))
                    .password("1234")
                    .role(RoleType.USER_ROLE)
                    .build());

            for (int j = 0; j < 80; j++) {
                var wishDraw = wishDrawRespository.save(WishDraw.builder()
                        .member(member)
                        .name(String.format("draw%s", j))
                        .build());

                for (int k = 10 * j; k < 10 * j + 9; k++) {
                    if(k > products.size() - 1) {
                        break;
                    }
                    wishRepository.save(Wish.builder()
                            .wishDraw(wishDraw)
                            .member(member)
                            .product(products.get(k))
                            .build());
                }
            }
        }
    }

    @SneakyThrows
    public List<String[]> getListByCsv(String fileName){
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("dummy/" + fileName).toURI()), StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        list.remove(0);
        return list;
    }
}
