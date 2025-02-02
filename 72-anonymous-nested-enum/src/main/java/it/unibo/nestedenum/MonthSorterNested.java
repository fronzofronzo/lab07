package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        JANUARY("January",31),
        FEBRUARY("February",28),
        MARCH("March",31),
        APRIL("April",30),
        MAY("May",31),
        JUNE("June",30),
        JULY("July",31),
        AUGUST("August",31),
        SEPTEMBER("September",30),
        OCTOBER("October",31),
        NOVEMBER("November", 30),
        DECEMBER("December",31);

        private final String name;
        private final int numDays;

        private Month(final String name, final int actualNumOfDays) {
            this.name = name;   
            this.numDays = actualNumOfDays;
        }

        public static Month fromString(String text) {
            int corrispondences = 0;
            Month matchMonth = null; 
            for(Month m : Month.values()) {
                final String toUpper = m.name.toUpperCase();
                final String toLower = m.name.toLowerCase();
                    if(text.length() <= m.name.length()) {
                        if(text.compareTo(m.name.substring(0,text.length())) == 0 || text.compareTo(toUpper.substring(0,text.length()) )==
                        0 || text.compareTo(toLower.substring(0,text.length())) == 0) {
                            corrispondences++;
                            matchMonth = m;
                        }
                    }
            }
            if(corrispondences == 0) {
                throw new IllegalArgumentException("No Month with such name");
            }
            if(corrispondences > 1) {
                throw new IllegalArgumentException("Ambigous Input");
            }
            return matchMonth;
        }

        public String getName() {
            return this.name;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                final Month m1 = Month.fromString(s1);
                final Month m2 = Month.fromString(s2);
                if(m1.numDays == m2.numDays) {
                    return 0;
                }
                if(m1.numDays > m2.numDays) {
                    return 1;
                }
                return -1;
            }
            
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {

            @Override
            public int compare(String string1, String string2) {
                final Month m1 = Month.fromString(string1);
                final Month m2 = Month.fromString(string2);
                if(m1.ordinal() == m2.ordinal()) {
                    return 0;
                }
                if(m1.ordinal() > m2.ordinal()) {
                    return 1;
                }
                return -1;
            }
            
        };
    }
}
