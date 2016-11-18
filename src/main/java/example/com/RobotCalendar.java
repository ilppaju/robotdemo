package example.com

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

/**

 * Kirjasto otetaan käyttöön robotissa lisäämällä settings osioon:
 * Library         example.com.RobotCalendar
 * Tämän jälkeen kirjaston keywordit on käytettävissä robotin puolelle.
 * <p/>
 *
 * @author pajuuilp
 */
public class RobotCalendar {
    public static final String ROBOT_LIBRARY_SCOPE = "TEST SUITE";
    private static final RobotLogger log = new RobotLogger();

    /**
     * Palauttaa viikon numeron tästä hetkestä eteenpäin
     *
     * @param num viikkoja eteenpäin
     * @return viikon numero muodssa yyyyww esim. 201401
     */
    public static String viikkojaEteenpain(int num) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        DateTime dtNow = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        dt = dt.plusWeeks(num);
        dt = dt.withDayOfWeek(DateTimeConstants.SUNDAY);
        if (dt.getWeekOfWeekyear()>= 52 && dt.getYear() > dtNow.getYear())
        	return dtNow.toString("yyyy") + dt.getWeekOfWeekyear();
        else
        	return dt.toString("yyyyww");
        
    }
    

    /**
     * Palauttaa viikon numeron tästä hetkestä eteenpäin
     *
     * @param num viikkoja eteenpäin
     * @return viikon numero muodssa yyyyww esim. 201401
     */
    public static String viikkojaEteenpain(String luku) {
    	return viikkojaEteenpain(Integer.parseInt(luku));
        
    }
    
    
    public static String viikkojaEteenpain(DateTime paivaMaara, int num) {
        DateTime dt = new DateTime(paivaMaara, DateTimeZone.forID("Europe/Helsinki"));
        DateTime dtNow = new DateTime(paivaMaara, DateTimeZone.forID("Europe/Helsinki"));
        dt = dt.plusWeeks(num);
        dt = dt.withDayOfWeek(DateTimeConstants.SUNDAY);
        if (dt.getWeekOfWeekyear()>= 52 && dt.getYear() > dtNow.getYear())
        	return dtNow.toString("yyyy") + dt.getWeekOfWeekyear();
        else
        	return dt.toString("yyyyww");
        
    }

    /**
     * Palauttaa TRUE jos parametrina annettu viikko on parillinen, FALSE muutoin
     *
     * @param viikko viikon id muodossa yyyyww
     * @return TRUE jos viikko on parillinen
     */
    public static boolean onkoParillinenViikko(String viikko) {
        DateTime dt = DateTime.parse(viikko, DateTimeFormat.forPattern("yyyyww"));
        int wk = dt.getWeekOfWeekyear();

        log.debug("viikkonumero: " + wk);

        return ((wk % 2) == 0);
    }

    /**
     * Palauttaa viikon numeron nykyhetkestä taaksepäin
     *
     * @param num viikkoja taaksepäin
     * @return viikon numero muodossa yyyyww -> 201401
     */
    public static String viikkojaTaaksepain(int num) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        DateTime dtNow = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        dt = dt.minusWeeks(num);
        dt = dt.withDayOfWeek(DateTimeConstants.SUNDAY);
        if (dt.getWeekOfWeekyear()>= 52 && dt.getYear() < dtNow.getYear())
        	return dt.toString("yyyy") + dt.getWeekOfWeekyear();
        else
        	return dt.toString("yyyyww");
    }

    
    /**
     * Palauttaa viikon numeron nykyhetkestä taaksepäin
     *
     * @param num viikkoja taaksepäin
     * @return viikon numero muodossa yyyyww -> 201401
     */
    public static String viikkojaTaaksepain(String num) {
        return viikkojaTaaksepain(Integer.parseInt(num));
    }
    
    public static String viikkojaTaaksepain(DateTime paivaMaara, int num) {
        DateTime dt = new DateTime(paivaMaara, DateTimeZone.forID("Europe/Helsinki"));
        DateTime dtNow = new DateTime(paivaMaara, DateTimeZone.forID("Europe/Helsinki"));
        dt = dt.minusWeeks(num);
        if (dt.getWeekOfWeekyear()>= 52 && dt.getYear() < dtNow.getYear())
        	return dt.toString("yyyy") + dt.getWeekOfWeekyear();
        else
        	return dt.toString("yyyyww");
    }

    /**
     * Palauttaa nykyhetken muodossa d.M.yyyy
     *
     * @return
     */
    public static String nykyHetki() {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));

        return dt.toString("d.M.yyyy");
    }

    /**
     * Palauttaa annetulta viikolta halutun viikonpäivän päivämäärän muodossa dd.MM.yyyy
     *
     * @param viikko viikon id muodossa yyyyww
     * @param paiva  maanantai = 1, tiistai=2 jne...
     * @return
     */
    public static String annaViikonpaiva(String viikko, int paiva) {
        DateTime dt = DateTime.parse(viikko, DateTimeFormat.forPattern("yyyyww"));

        switch (paiva) {
            case 1:
                dt = dt.withDayOfWeek(DateTimeConstants.MONDAY);
                break;
            case 2:
                dt = dt.withDayOfWeek(DateTimeConstants.TUESDAY);
                break;
            case 3:
                dt = dt.withDayOfWeek(DateTimeConstants.WEDNESDAY);
                break;
            case 4:
                dt = dt.withDayOfWeek(DateTimeConstants.THURSDAY);
                break;
            case 5:
                dt = dt.withDayOfWeek(DateTimeConstants.FRIDAY);
                break;
            case 6:
                dt = dt.withDayOfWeek(DateTimeConstants.SATURDAY);
                break;
            default:
                dt = dt.withDayOfWeek(DateTimeConstants.SUNDAY);
                break;
        }

        return dt.toString("d.M.yyyy");
    }

    /**
     * Palauttaa päivämäärän nykyhetkestä eteenpäin muodossa d.M.yyyy
     *
     * @param num päiviä eteenpäin
     * @return ajanhetki nykyhetkestä eteenpäin
     */
    public static String paiviaEteenpain(int num) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));

        return dt.plusDays(num).toString("d.M.yyyy");
    }

    /**
     * Palauttaa päivämäärän nykyhetkestä eteenpäin muodossa d.M.yyyy
     *
     * @param num päiviä eteenpäin
     * @return ajanhetki nykyhetkestä eteenpäin
     */
    public static String paiviaTaaksepain(int num) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));

        return dt.minusDays(num).toString("d.M.yyyy");
    }


    /**
     * Kasvattaa annettua päivämäärää halutulla määrällä ja palauttaa halutussa muodossa
     *
     * @param pvm päivämäärä muodossa dd.MM.yyyy
     * @param num päivien määrä eteenpäin
     * @return ajanhetki käyttäjän antamassa formaatissa
     */
    public static String paiviaHetkestaEteenpain(String pvm, int num, String format) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        dt = DateTime.parse(pvm, DateTimeFormat.forPattern("dd.MM.yyyy"));

        return dt.plusDays(num).toString(format);
    }

    /**
     * Palauttaa päivämäärän käyttäjän haluamassa formaatista ennetusta ajanhetkestä taaksepäin.
     *
     * @param pvm    haluttu ajanhetki, josta lasketaan päiviä taaksenpäin
     * @param num    päivien määrä taaksepäin
     * @param format päivämäärän esitysmuoto esim. dd.MM.yyyy -> 04.12.2014
     * @return ajanhetki käyttäjän antamassa formaatissa
     */
    public static String paiviaHetkestaTaaksepain(String pvm, int num, String format) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        dt = DateTime.parse(pvm, DateTimeFormat.forPattern("dd.MM.yyyy"));

        return dt.minusDays(num).toString(format);
    }

    /**
     * Muokkaa päivämäärän muodossa dd.MM.yyyy haluttuun formaattiin
     *
     * @param pvm    päivämäärä muodossa dd.MM.yyy
     * @param format haluttu päivämäärän formaatti esim. yyyy-MM-dd
     * @return päiväys käyttäjän antamassa formaatissa.
     */
    public static String muokkaaEsitystapaa(String pvm, String format) {
        DateTime dt = new DateTime(DateTimeZone.forID("Europe/Helsinki"));
        dt = DateTime.parse(pvm, DateTimeFormat.forPattern("dd.MM.yyyy"));

        return dt.toString(format);
    }
}


