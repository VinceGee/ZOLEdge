package zw.co.vokers.zoledge.models;

import java.util.Arrays;
import java.util.List;

import zw.co.vokers.zoledge.R;

/**
 * Created by VinceGee on 3/21/2018.
 */

public class GenreDataFactory {
    public static List<Banks> makeGenres() {
        return Arrays.asList(getEcocash(),
                getCabs(),
                getNMB(),
                getStanbic(),
                getStanChart(),
                getSteward());
    }

    public static Banks getEcocash() {
        return new Banks("EcoCash", getEcocashDetails(), R.drawable.pecocash);
    }

    public static List<Artist> getEcocashDetails() {
        Artist queen = new Artist("Dial *151#", true);
        Artist styx = new Artist("Enter your EcoCash pin", false);
        Artist reoSpeedwagon = new Artist("Select option 2. Make Payment", false);
        Artist boston = new Artist("Then option 1. Pay Bill", true);
        Artist boston1 = new Artist("Enter our biller code 01866", true);
        Artist boston2 = new Artist("Enter the amount you'd like to pay", true);
        Artist boston3 = new Artist("Enter the numeric part of your ZOL ID", true);
        Artist boston4 = new Artist("Reviewing the transaction details, and enter 1 to confirm.", true);

        return Arrays.asList(queen, styx, reoSpeedwagon, boston,boston1,boston2,boston3,boston4);
    }

    public static Banks getCabs() {
        return new Banks("Cabs", getCabsDetails(), R.drawable.pcabs);
    }

    public static List<Artist> getCabsDetails() {
        Artist milesDavis = new Artist("Mobile Bank USSD: *227#", true);
        Artist ellaFitzgerald = new Artist("Branch: Platinum Club", true);
        Artist billieHoliday = new Artist("Account Number: 1003140424", false);

        return Arrays.asList(milesDavis, ellaFitzgerald, billieHoliday);
    }

    public static Banks getNMB() {
        return new Banks("NMB", getNMBDetails(), R.drawable.pnmb);
    }

    public static List<Artist> getNMBDetails() {
        Artist beethoven = new Artist("Mobile Bank USSD: *240#", false);
        Artist bach = new Artist("Branch: Eastgate", true);
        Artist brahms = new Artist("Account Number: 240064332", false);
        Artist puccini = new Artist("Swift Code: NMBLZWHX", false);

        return Arrays.asList(beethoven, bach, brahms, puccini);
    }

    public static Banks getStanbic() {
        return new Banks("Stanbic", getStanbicDetails(), R.drawable.pstanbic);
    }

    public static List<Artist> getStanbicDetails() {
        Artist hectorLavoe = new Artist("Mobile Bank USSD: *247#", true);
        Artist celiaCruz = new Artist("Branch: Nelson Mandela", false);
        Artist willieColon = new Artist("Account Number: 0222039070450", false);
        Artist marcAnthony = new Artist("Swift Code: SBICZWHX", false);

        return Arrays.asList(hectorLavoe, celiaCruz, willieColon, marcAnthony);
    }

    public static Banks getStanChart() {
        return new Banks("Standard Chartered", getStanChartDetails(), R.drawable.pstanchart);
    }

    public static List<Artist> getStanChartDetails() {
        Artist billMonroe = new Artist("Branch: Borrowdale", false);
        Artist earlScruggs = new Artist("Account Number: 8700212269000", false);
        Artist osborneBrothers = new Artist("Swift Code: SCBLZWHX", true);

        return Arrays.asList(billMonroe, earlScruggs, osborneBrothers);
    }

    public static Banks getSteward() {
        return new Banks("Steward Bank", getStewardDetails(), R.drawable.psteward);
    }


    public static List<Artist> getStewardDetails() {
        Artist billMonroe = new Artist("Mobile Bank USSD: *210#", false);
        Artist earlScruggs = new Artist("Branch: Eastgate", false);
        Artist osborneBrothers = new Artist("Account Number: 1001967513", true);
        Artist johnHartford = new Artist("Swift Code: SCBLZWHX", false);

        return Arrays.asList(billMonroe, earlScruggs, osborneBrothers, johnHartford);
    }
}
