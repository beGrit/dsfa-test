import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.IAccountManager;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.impl.AccountManagerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    private IAccountManager service = new AccountManagerImpl();

    private boolean checkBirth(String birth) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = sdf.parse(birth);
//        String date2=sdf.format(date);
        boolean b = new Date().after(parsed);
        System.out.println(b);
        System.out.println(parsed);
        return true;
    }

    public static void main(String[] args) throws ParseException {
        Test test = new Test();
//        Result result = test.service.getRandomAccountName();
//        System.out.println(result);
        test.checkBirth("2020-01-02");
    }
}
