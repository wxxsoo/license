package license.entity.license;

import license.util.EnumModel;

/**
 * Created by wansoo.
 * User: accomplishlee
 */
public enum ProductCode implements EnumModel {
    MOBILE("Mobile Framework"), WEB("Web Framework");

    private String value;

    private ProductCode(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return name();
    }

    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return value;
    }
}
