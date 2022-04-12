package data.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Getter
@Service
@Slf4j
public class CollectionIncrementer<O ,T extends Collection<O >>  {

    @Timed
    public void increment(T collection){
        for (int i = 0; i < 100000; i++) {
            collection.add( (O) new Object());

        }
        log.info("done for: " + collection.getClass());
    }
}
