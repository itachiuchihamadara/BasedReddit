 package com.based.genzedong.BasedReddit;

import com.based.genzedong.BasedReddit.services.PostServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

 @SpringBootApplication
public class BasedRedditApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BasedRedditApplication.class, args);

		List<Integer> st = new ArrayList<>();
		st.add(10); st.add(20);

		int i = st.stream().reduce(0, Integer::sum);
		System.out.println(i);

	}

}
