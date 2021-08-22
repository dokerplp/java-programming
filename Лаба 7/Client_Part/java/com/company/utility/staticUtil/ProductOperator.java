package com.company.utility.staticUtil;

import com.company.data.OrganizationType;
import com.company.data.UnitOfMeasure;
import com.company.exceptions.IncorrectDataException;
import com.company.memory.Registers;

/**
 * Utility for creating product's fields
 */
public class ProductOperator {

    private final Registers registers;
    private final DataOperator operator;

    public ProductOperator(Registers registers, DataOperator operator) {
        this.registers = registers;
        this.operator = operator;
    }

    /**
     * Checking product name
     *
     * @return name if it was correct
     * @throws IncorrectDataException if it was not
     */
    private String getName() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("Название продукта: ");
        String name = operator.getSTRING();
        if (Check.NotNullCheck(name) && Check.NotEmptyLineCheck(name)) return name;
        else throw new IncorrectDataException("Название продукта - непустая строка");
    }

    public String NAME() {
        while (true) {
            try {
                return getName();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking x coordinate
     *
     * @return x coordinate if it was correct
     * @throws IncorrectDataException if it was not
     */
    private Float getX() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("     X: ");
        Float X = operator.getFLOAT();
        if (Check.NotNullCheck(X)) return X;
        else throw new IncorrectDataException("X - дробное число");
    }

    public Float X() {
        while (true) {
            try {
                return getX();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking y coordinate
     *
     * @return y coordinate if it was correct
     * @throws IncorrectDataException if it was not
     */
    private Integer getY() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("     Y: ");
        Integer Y = operator.getINT();
        if (!Check.NotNullCheck(Y)) throw new IncorrectDataException("Y - целое число");
        else {
            if (Y <= 125) return Y;
            else throw new IncorrectDataException("Максимальное значение Y = 125");
        }
    }

    public Integer Y() {
        while (true) {
            try {
                return getY();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking product price
     *
     * @return price if it was correct
     * @throws IncorrectDataException if it was not
     */
    private Long getPrice() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("Цена: ");
        Long price = operator.getLONG();
        if (!Check.NotNullCheck(price)) throw new IncorrectDataException("Цена - целое число");
        else {
            if (Check.AboveZeroCheck(price)) return price;
            else throw new IncorrectDataException("Цена должна быть больше 0");
        }
    }

    public Long PRICE() {
        while (true) {
            try {
                return getPrice();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking product part number
     *
     * @return part number if it was correct
     * @throws IncorrectDataException if it was not
     */
    private String getPartNumber() {
        if (registers.isMode()) System.out.print("Артикул: ");
        String partNumber = operator.getSTRING();
        if (partNumber == null) return "";
        else return partNumber;
    }

    public String PART_NUMBER() {
        while (true) {
            try {
                return getPartNumber();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking product manufacture cost
     *
     * @return manufacture cost if it was correct
     * @throws IncorrectDataException if it was not
     */
    private Float getMCost() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("Цена производства: ");
        Float cost = operator.getFLOAT();
        if (!Check.NotNullCheck(cost)) throw new IncorrectDataException("Цена производства - дробное число");
        else {
            if (Check.AboveZeroCheck(cost) || cost == 0) return cost;
            else throw new IncorrectDataException("Цена производства должна быть больше или равна нулю");
        }
    }

    public Float COST() {
        while (true) {
            try {
                return getMCost();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking product unit of measure
     *
     * @return unit of measure if it was correct
     * @throws IncorrectDataException if it was not
     */
    private UnitOfMeasure getMeasure() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("Выберите единицы измерения (1 - Метры, 2 - Сантиметры, 3 - Литры): ");
        String switcher = operator.getSTRING();
        if (switcher == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
        else {
            UnitOfMeasure unitOfMeasure = null;
            switch (switcher) {
                case "1":
                    unitOfMeasure = UnitOfMeasure.METERS;
                    break;
                case "2":
                    unitOfMeasure = UnitOfMeasure.CENTIMETERS;
                    break;
                case "3":
                    unitOfMeasure = UnitOfMeasure.LITERS;
                    break;
                default:
                    break;
            }

            if (unitOfMeasure == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
            else return unitOfMeasure;
        }
    }

    public UnitOfMeasure MEASURE() {
        while (true) {
            try {
                return getMeasure();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking organization name
     *
     * @return name if it was correct
     * @throws IncorrectDataException if it was not
     */
    private String getOrgName() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("     Название компании: ");
        String name = operator.getSTRING();
        if (Check.NotNullCheck(name) && Check.NotEmptyLineCheck(name)) return name;
        else throw new IncorrectDataException("Название компании - непустая строка");
    }

    public String ORG_NAME() {
        while (true) {
            try {
                return getOrgName();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking organization employees count
     *
     * @return count if it was correct
     * @throws IncorrectDataException if it was not
     */
    private Long getEcount() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("     Количество работников: ");
        Long count = operator.getLONG();
        if (!Check.NotNullCheck(count)) throw new IncorrectDataException("Количество работников - целое число");
        else {
            if (Check.AboveZeroCheck(count)) return count;
            else throw new IncorrectDataException("Количество работников должно быть больше 0");
        }
    }

    public Long E_COUNT() {
        while (true) {
            try {
                return getEcount();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking organization type
     *
     * @return type if it was correct
     * @throws IncorrectDataException if it was not
     */
    private OrganizationType getType() throws IncorrectDataException {
        if (registers.isMode())
            System.out.print("     Тип компании: (1 - Общественная, 2 - Правительственная, 3 - ЗАО): ");
        String switcher = operator.getSTRING();
        if (switcher == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
        else {
            OrganizationType type = null;
            switch (switcher) {
                case "1":
                    type = OrganizationType.PUBLIC;
                    break;
                case "2":
                    type = OrganizationType.GOVERNMENT;
                    break;
                case "3":
                    type = OrganizationType.PRIVATE_LIMITED_COMPANY;
                    break;
                default:
                    break;
            }
            if (type == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
            else return type;
        }
    }

    public OrganizationType TYPE() {
        while (true) {
            try {
                return getType();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking organization street
     *
     * @return street if it was correct
     * @throws IncorrectDataException if it was not
     */
    private String getStreet() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("          Улица: ");
        String street = operator.getSTRING();
        if (Check.NotNullCheck(street)) return street;
        else throw new IncorrectDataException("Адресс - непустая строка");
    }

    public String STREET() {
        while (true) {
            try {
                return getStreet();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }

    /**
     * Checking organization zip code
     *
     * @return zip code if it was correct
     * @throws IncorrectDataException if it was not
     */
    private String getZip() throws IncorrectDataException {
        if (registers.isMode()) System.out.print("          Почтовый код: ");
        String zip = operator.getSTRING();
        if (zip == null) return "";
        else if (zip.length() <= 12) return zip;
        else throw new IncorrectDataException("Почтовый индекс - меньше 12 символов");
    }

    public String ZIP() {
        while (true) {
            try {
                return getZip();
            } catch (IncorrectDataException e) {
                e.outLogs();
            }
        }
    }
}
