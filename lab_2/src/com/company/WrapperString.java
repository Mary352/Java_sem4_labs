package com.company;

import java.util.Objects;

public class WrapperString {
    private  String strNew;

    public WrapperString(String str) {
        this.strNew = str;
    }

    public WrapperString() {

    }

    public String getStrNew() {
        return strNew;
    }

    public void setStrNew(String strNew) {
        this.strNew = strNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WrapperString)) return false;
        WrapperString that = (WrapperString) o;
        return Objects.equals(getStrNew(), that.getStrNew());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrNew());
    }

    @Override
    public String toString() {
        return "WrapperString{" +
                "str='" + strNew + '\'' +
                '}';
    }

    public void replace (char oldchar, char newchar)
    {
        strNew = strNew.replace(oldchar, newchar);
        System.out.println(strNew);
    }


}
