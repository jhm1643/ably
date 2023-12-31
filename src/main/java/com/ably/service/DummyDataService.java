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
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
                    .password(bCryptPasswordEncoder.encode("1234"))
                    .role(RoleType.USER_ROLE)
                    .build());

            for (int j = 0; j < 50; j++) {
                var wishDraw = wishDrawRespository.save(WishDraw.builder()
                        .member(member)
                        .name(String.format("draw%s", j))
                        .build());

                for (int k = 30 * j; k < 30 * j + 29; k++) {
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

        ClassPathResource resource = new ClassPathResource("dummy/" + fileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()));
        List<String[]> list = csvReader.readAll();
        csvReader.close();
        list.remove(0);
        return list;
    }
}
