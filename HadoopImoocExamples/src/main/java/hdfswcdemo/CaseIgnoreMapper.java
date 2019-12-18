package hdfswcdemo;

public class CaseIgnoreMapper implements IMapper {

    public void map(MapperContext c, String l) {
        String[] words = l.split("\\s");
        for (String w : words){
            c.put(w);
        }
    }

}
