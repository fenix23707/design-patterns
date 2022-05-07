package by.vsu.kovzov;

import by.vsu.kovzov.comparators.SpecializationLawyerComparator;
import by.vsu.kovzov.comparators.StartDateLawsuitComparator;
import by.vsu.kovzov.controlles.LawController;
import by.vsu.kovzov.factory.BuilderFactory;
import by.vsu.kovzov.factory.SingletonBuilderFactory;
import by.vsu.kovzov.service.LawyerService;
import by.vsu.kovzov.service.LawyerServiceImpl;
import org.apache.commons.cli.*;

public class Runner {
    private static CommandLine cmd;

    public static void main(String[] args) {
        init(args);

        BuilderFactory builderFactory = SingletonBuilderFactory.getInstance();
        LawyerService lawyerService = new LawyerServiceImpl();

        LawController lawController = new LawController(builderFactory, lawyerService);

        lawController.loadLawyers(cmd.getOptionValue("lawyer"));
        lawController.enrichWithLawsuits(cmd.getOptionValue("lawsuit"));
        lawController.sortLawsuits(new StartDateLawsuitComparator());
        lawController.enrichEfficiency();
        lawController.sortEnrichedLawyers(new SpecializationLawyerComparator());

        lawController.getResult().forEach(System.out::println);
    }

    private static void init(String[] args) {
        Options options = new Options();

        Option lawyer = new Option("lawyer", true, "path to lawyer file");
        lawyer.setRequired(true);
        options.addOption(lawyer);

        Option lawsuit = new Option("lawsuit", true, "path to lawsuit file");
        lawsuit.setRequired(true);
        options.addOption(lawsuit);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("law-utility", options);

            System.exit(1);
        }
    }
}
