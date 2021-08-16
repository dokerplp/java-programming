package com.company.utility.forCommands;

import com.company.data.OrganizationType;
import com.company.data.UnitOfMeasure;
import com.company.exceptions.IncorrectDataException;
import com.company.utility.forData.DataChecker;
import com.company.utility.forData.DataOperator;


/**
 * Checking correctness of data
 */
public class ProductOperator extends DataOperator {

    private final RequestsQueue queue;

    /**
     * ProductOperator Constructor
     * @param queue - queue
     */
    public ProductOperator(RequestsQueue queue) {
        super(queue);
        this.queue = queue;
    }

    /**
     * Checking product name
     * @return name if it was correct
     * @throws IncorrectDataException if it was not
     */
    public String getName() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("Название продукта: ");
        String name = getSTRING();
        if (DataChecker.NotNullCheck(name) && DataChecker.NotEmptyLineCheck(name)) return name;
        else throw new IncorrectDataException("Название продукта - непустая строка");
    }

    /**
     * Checking x coordinate
     * @return x coordinate if it was correct
     * @throws IncorrectDataException if it was not
     */
    public Float getX() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("     X: ");
        Float X = getFLOAT();
        if (DataChecker.NotNullCheck(X)) return X;
        else throw new IncorrectDataException("X - дробное число");
    }

    /**
     * Checking y coordinate
     * @return y coordinate if it was correct
     * @throws IncorrectDataException if it was not
     */
    public Integer getY() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("     Y: ");
        Integer Y = getINT();
        if (!DataChecker.NotNullCheck(Y)) throw new IncorrectDataException("Y - целое число");
        else {
            if (Y <= 125) return Y;
            else throw new IncorrectDataException("Максимальное значение Y = 125");
        }
    }

    /**
     * Checking product price
     * @return price if it was correct
     * @throws IncorrectDataException if it was not
     */
    public Long getPrice() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("Цена: ");
        Long price = getLONG();
        if (!DataChecker.NotNullCheck(price)) throw new IncorrectDataException("Цена - целое число");
        else {
            if (DataChecker.AboveZeroCheck(price)) return price;
            else throw new IncorrectDataException("Цена должна быть больше 0");
        }
    }

    /**
     * Checking product part number
     * @return part number if it was correct
     * @throws IncorrectDataException if it was not
     */
    public String getPartNumber(){
        if (queue.isStatus()) System.out.print("Артикул: ");
        String partNumber = getSTRING();
        if (partNumber == null) return "";
        else return partNumber;
    }

    /**
     * Checking product manufacture cost
     * @return manufacture cost if it was correct
     * @throws IncorrectDataException if it was not
     */
    public Float getMCost() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("Цена производства: ");
        Float cost = getFLOAT();
        if (!DataChecker.NotNullCheck(cost)) throw new IncorrectDataException("Цена производства - дробное число");
        else {
            if (DataChecker.AboveZeroCheck(cost) || cost == 0) return cost;
            else throw new IncorrectDataException("Цена производства должна быть больше или равна нулю");
        }
    }

    /**
     * Checking product unit of measure
     * @return unit of measure if it was correct
     * @throws IncorrectDataException if it was not
     */
    public UnitOfMeasure getMeasure() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("Выберите единицы измерения (1 - Метры, 2 - Сантиметры, 3 - Литры): ");
        String switcher = getSTRING();
        if (switcher == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
        else{
            UnitOfMeasure unitOfMeasure = null;
            switch (switcher) {
                case "1":
                    unitOfMeasure = UnitOfMeasure.CENTIMETERS;
                    break;
                case "2":
                    unitOfMeasure = UnitOfMeasure.METERS;
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

    /**
     * Checking organization name
     * @return name if it was correct
     * @throws IncorrectDataException if it was not
     */
    public String getOrgName() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("     Название компании: ");
        String name = getSTRING();
        if (DataChecker.NotNullCheck(name) && DataChecker.NotEmptyLineCheck(name)) return name;
        else throw new IncorrectDataException("Название компании - непустая строка");
    }

    /**
     * Checking organization employees count
     * @return count if it was correct
     * @throws IncorrectDataException if it was not
     */
    public Long getEcount() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("     Количество работников: ");
        Long count = getLONG();
        if (!DataChecker.NotNullCheck(count)) throw new IncorrectDataException("Количество работников - целое число");
        else {
            if (DataChecker.AboveZeroCheck(count)) return count;
            else throw new IncorrectDataException("Количество работников должно быть больше 0");
        }
    }

    /**
     * Checking organization type
     * @return type if it was correct
     * @throws IncorrectDataException if it was not
     */
    public OrganizationType getType() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("     Тип компании: (1 - Общественная, 2 - Правительственная, 3 - ЗАО): ");
        String switcher = getSTRING();
        if (switcher == null) throw new IncorrectDataException("Пожалуйста, введите 1, 2 или 3");
        else{
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
    /**
     * Checking organization street
     * @return street if it was correct
     * @throws IncorrectDataException if it was not
     */
    public String getStreet() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("          Улица: ");
        String street = getSTRING();
        if (DataChecker.NotNullCheck(street)) return street;
        else throw new IncorrectDataException("Адресс - непустая строка");
    }
    /**
     * Checking organization zip code
     * @return zip code if it was correct
     * @throws IncorrectDataException if it was not
     */
    public String getZip() throws IncorrectDataException{
        if (queue.isStatus()) System.out.print("          Почтовый код: ");
        String zip = getSTRING();
        if (zip == null) return "";
        else if (zip.length() <= 12) return zip;
        else throw new IncorrectDataException("Почтовый индекс - меньше 12 символов");
    }

}
