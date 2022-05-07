package by.vsu.kovzov;

import by.vsu.kovzov.builder.lawsuit.LawsuitBuilder;
import by.vsu.kovzov.builder.lawyer.LawyerBuilder;
import by.vsu.kovzov.factory.BuilderFactory;
import by.vsu.kovzov.factory.SingletonBuilderFactory;
import org.apache.commons.cli.*;

public class Runner {
    private static CommandLine cmd;

    public static void main(String[] args) {
        init(args);

        BuilderFactory builderFactory = SingletonBuilderFactory.getInstance();

        LawyerBuilder lawyerBuilder = builderFactory.getLawyerBuilder(cmd.getOptionValue("lawyer"));
        LawsuitBuilder lawsuitBuilder = builderFactory.getLawsuitBuilder(cmd.getOptionValue("lawsuit"));

        System.out.println(lawyerBuilder.getAllLawyers());
        System.out.println();
        System.out.println(lawsuitBuilder.getAllLawsuits());
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
