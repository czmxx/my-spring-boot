package base.demo;

/**
 * Created by CHENZHANMEI on 2017/3/13.
 */
public interface PersonFactory<P extends Person> {

    P create(String firstName, String lastName);
}
