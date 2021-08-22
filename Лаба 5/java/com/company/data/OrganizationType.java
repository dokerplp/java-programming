package com.company.data;

/**
 * Enum of possible organization types
 */
public enum OrganizationType {

    PUBLIC ("Общественная"),
    GOVERNMENT ("Правительственная"),
    PRIVATE_LIMITED_COMPANY ("ЗАО");


    private final String type;
    /**
     * @return how it calls in Russian
     */
    public String getType(){
        return type;
    }

    /**
     * Constructor
     * @param type - name
     */
    OrganizationType(String type){
        this.type = type;
    }

    /**
     * Method matches russian name to the enum object
     * @param measure - String of russian name
     * @return Organization type or null
     */
    public static OrganizationType reverse(String measure){

        switch (measure){
            case "Общественная":
                return OrganizationType.PUBLIC;
            case "Правительственная":
                return OrganizationType.GOVERNMENT;
            case "ЗАО":
                return OrganizationType.PRIVATE_LIMITED_COMPANY;
            default:
                return null;
        }

    }
}
