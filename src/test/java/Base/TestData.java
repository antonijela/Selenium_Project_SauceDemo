package Base;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData(){
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData(){
        return new Object[][]{
                {"sdfcgvhb", "secret"},
                {"2345678", "345678"},
                {"qufvq fuiaf", "725 qhgrf"},
                {".-!@#$%^&", "!@#$%^&*"},
        };
    }

    @DataProvider(name = "emptyFieldsData")
    public Object[][] getEmptyFieldsData(){
        return new Object[][]{
                {"", "secret_sauce"},
                {"standard_user", ""},
                {"", ""}
        };
    }
}
