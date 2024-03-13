package soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MySource {
    List<String> defaulters;
    public MySource(){
        defaulters= Stream.of("Annapoo","Aru","eeksha").collect(Collectors.toList());
    }
    @WebMethod
    public String addDefaulter( String name){
        defaulters.add(name);
        return name+" has added to the defaulters record";
    }
}
