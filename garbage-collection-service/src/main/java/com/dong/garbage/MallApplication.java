package com.dong.garbage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.*;


@SpringBootApplication
//开启Eureka配置，将此服务注册到eureka注册中心
@EnableEurekaClient
public class MallApplication extends Thread{
    public static void main(String[] args) throws Exception {
        //SpringApplication.run(MallApplication.class, args);
		for (int i = 1; i<10;i++)
		new MallApplication().start();
        List<String> list = new ArrayList<>();
        list.add("aaa");
		String[] objects = list.toArray(new String[list.size()]);
		System.out.println(objects[0]);
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next);
		}
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		});
		thread.start();
		List<String> strings2 = Collections.unmodifiableList(list);
		//strings2.add("aaaaa");
		List<String> strings = Arrays.asList(objects);
		List<String> strings1 = new ArrayList<String>(objects.length);
		String[] strArray = { "array-a", "array-b" };
		Collections.addAll(strings1, objects);
		strings1.add("aaa");
		list.add("bbbb");
        for (String a : list) {
            System.out.println(a);
            list.remove(a);
        }
        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        map.put("b", "b");
        map.put("a", "b");
        for (String a :
                map.keySet()) {
            System.out.println(a);
        }
    }

	@Override
	public void run() {
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("aaaa");

	}
}