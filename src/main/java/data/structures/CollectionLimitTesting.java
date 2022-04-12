package data.structures;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;


@SpringBootApplication
public class CollectionLimitTesting implements ApplicationContextAware {

    private ApplicationContext context;

    public static void main(String[] args) {
        new SpringApplicationBuilder(CollectionLimitTesting.class).profiles(args).run(args);
    }

    @Component
    @Profile("test")
    class Test implements CommandLineRunner {

        @Autowired
        CollectionIncrementer<Integer, ArrayList<Integer>> arrayIncrement;
        @Autowired
        CollectionIncrementer<Integer, LinkedList<Integer>> linkedIncrement;
        @Autowired
        CollectionIncrementer<Integer, Vector<Integer>> vectorIncrement;
        @Autowired
        CollectionIncrementer<Integer, Stack<Integer>> stackIncrement;
        @Autowired
        CollectionIncrementer<Integer, PriorityQueue<Integer>> prioQueIncrement;
        @Autowired
        CollectionIncrementer<Integer, HashSet<Integer>> hashSetIncrement;



        @Override
        public void run(String... args) throws Exception {

            ArrayList<Integer> arrayList = new ArrayList<>();
            LinkedList<Integer> linkedList = new LinkedList<>();
            Vector<Integer> vectorCollection = new Vector<>();
            Stack<Integer> stackCollection = new Stack<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            HashSet<Integer> hashSetCollection = new HashSet<>();


            ((ConfigurableApplicationContext) context).close();

            Thread array = new Thread(()->arrayIncrement.increment(arrayList));
            Thread linked = new Thread(() -> linkedIncrement.increment(linkedList));
            Thread vector = new Thread(() -> vectorIncrement.increment(vectorCollection));
            Thread stack = new Thread(() -> stackIncrement.increment(stackCollection));
            Thread prioQueue = new Thread(() -> prioQueIncrement.increment(priorityQueue));
            Thread hashSet = new Thread(() -> hashSetIncrement.increment(hashSetCollection));
            linked.start();
            array.start();
            vector.start();
            stack.start();
            hashSet.start();

        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
