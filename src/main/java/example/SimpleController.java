package example;

import com.lantingeee.annotations.ParameterValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lantingeee on 15/12/2017.
 */

@RestController
public class SimpleController {

    @RequestMapping
    public String getStringFormInput(@ParameterValue("input") String input){
        return input;
    }

}
