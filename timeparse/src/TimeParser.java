import java.io.*;
import java.time.Duration;

public class TimeParser {
    public static void main(String[] args){
        //luca
        System.out.println("Luca");
        printUse("C:\\Users\\hoffm\\Downloads\\Telegram Desktop\\luca.csv");

        //stefan
        System.out.println("Stefan");
        printUse("C:\\Users\\hoffm\\Downloads\\Telegram Desktop\\stefan.csv");

        //vera
        System.out.println("Vera");
        printUse("C:\\Users\\hoffm\\Downloads\\Telegram Desktop\\vera.csv");

        //bixby
        System.out.println("Bixby");
        printUse("C:\\Users\\hoffm\\Downloads\\Telegram Desktop\\bixby.csv");

        //tom
        System.out.println("Tom");
        printUse("C:\\Users\\hoffm\\Downloads\\Telegram Desktop\\tom.csv");
    }
    public static Duration extractTime(String[] data){
        String time = data[3];
        time = time.replaceAll("\"", "");
        String[] split = time.split(":");

        Duration timeSpent = Duration.ofHours((Integer.parseInt(split[0])));
        timeSpent = timeSpent.plusMinutes((Integer.parseInt(split[1])));
        return  timeSpent;
    }

    public static void printUse(String path){
        Duration manual = Duration.ofHours(0);
        Duration implementation = Duration.ofHours(0);
        Duration architectureDesign = Duration.ofHours(0);
        Duration requirements = Duration.ofHours(0);
        Duration projectplan = Duration.ofHours(0);
        Duration test = Duration.ofHours(0);


        try {
            BufferedReader rLuca = new BufferedReader(new FileReader(path));
            String row;

            while ((row = rLuca.readLine()) != null){
                String[] data = row.split(",");
                switch (data[0]) {
                    case "\"Benutzerhandbuch\"" :
                        manual = manual.plus(extractTime(data));
                        break;
                    case "\"Implementierung\"" :
                        implementation = implementation.plus(extractTime(data));
                        break;
                    case "\"Architekturentwurf\"" :
                        architectureDesign = architectureDesign.plus(extractTime(data));
                        break;
                    case "\"Anforderungsspezifikation\"" :
                        requirements = requirements.plus(extractTime(data));
                        break;
                    case "\"Projektplanung\"" :
                        projectplan = projectplan.plus(extractTime(data));
                        break;
                    case "\"Tests\"" :
                        test = test.plus(extractTime(data));
                        break;

                    default: break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Duration total = manual.plus(implementation).plus(architectureDesign).plus(requirements).plus(projectplan).plus(test);
        System.out.println("Benutzerhandbuch " + manual.toString().replaceAll("PT", ""));
        System.out.println("Implementierung " + implementation.toString().replaceAll("PT", ""));
        System.out.println("Architekturentwurf " + architectureDesign.toString().replaceAll("PT", ""));
        System.out.println("Anforderungsspezifikation " + requirements.toString().replaceAll("PT", ""));
        System.out.println("Projektplanung " + projectplan.toString().replaceAll("PT", ""));
        System.out.println("Tests " + test.toString().replaceAll("PT", ""));
        System.out.println("Geaamt" + total.toString().replaceAll("PT", ""));
        System.out.println("------------------------------------------------------------");
    }
}


