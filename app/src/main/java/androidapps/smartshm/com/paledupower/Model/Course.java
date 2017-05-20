package androidapps.smartshm.com.paledupower.Model;

/**
 * Created by mohammedh.alsamak on 5/19/17.
 */

public class Course {
    private String name;
    private String hour;
    private String nameCenter;
    private String img;
    private String dic;

    public String getDic() {

        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public Course() {

    }

    public Course(String name, String hour, String nameCenter , String dic) {
        this.name = name;
        this.hour = hour;
        this.nameCenter = nameCenter;
        this.dic = dic;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNameCenter() {
        return nameCenter;
    }

    public void setNameCenter(String nameCenter) {
        this.nameCenter = nameCenter;
    }
}
