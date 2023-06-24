package com.ably.service;

import com.ably.dto.product.request.ProductSaveRequest;
import com.ably.entity.Product;
import com.ably.repository.ProductRepository;
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
public class FileToDbService {

    private final ProductRepository productRepository;

    @PostConstruct
    public void csvToDbStart(){
        productRepository.saveAll(
                getListByCsv("dummy_product.csv").stream()
                        .map(data -> Product.createProduct(
                                ProductSaveRequest.builder()
                                        .name(data[0])
                                        .thumbnail(data[1])
                                        .price(Long.valueOf(data[2]))
                                        .build()
                        )).collect(Collectors.toList())
        );
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
