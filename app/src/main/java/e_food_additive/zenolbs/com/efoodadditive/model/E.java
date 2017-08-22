package e_food_additive.zenolbs.com.efoodadditive.model;

/**
 * Created by adex on 31.05.16.
 */
// IT IS POJO
public class E {

    private String path;
    private String codeNumber;
    private String eName;
    private Integer danger; //-1,0,1

    public E(String path,Integer danger ){
        this.path = path;
        this.danger = danger;

    }

    public E(String path, String codeNumber,String codeName, Integer danger ){
        this.path = path;
        this.codeNumber = codeNumber;
        this.eName = codeName;
        this.danger = danger;


    }


    public String getPath(){return path;}
    public String getCodeNumber(){return codeNumber;}
    public String getName() {return eName;}
    public Integer getDanger(){return danger;}

    public void setName(String codeName) {this.eName = codeName;}
    public void setPath(String path){this.path = path;}
    public void setCodeNumber(String codeNumber){this.codeNumber = codeNumber;}
    public void setDanger(Integer danger) {this.danger = danger;}
}
