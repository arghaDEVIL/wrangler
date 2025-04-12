package io.cdap.wrangler.directives.aggregate;

import io.cdap.wrangler.api.*;
import io.cdap.wrangler.api.parser.*;
import io.cdap.wrangler.api.annotations.PublicEvolving;

import java.util.ArrayList;
import java.util.List;

@PublicEvolving
public class AggregateStats implements Directive {
    private String sizeCol;
    private String timeCol;
    private String outputSizeCol;
    private String outputTimeCol;

    private long totalBytes = 0;
    private long totalNanos = 0;

    @Override
    public UsageDefinition define() {
        UsageDefinition.Builder builder = UsageDefinition.builder("aggregate-stats");
        builder.define("sizeColumn", TokenType.IDENTIFIER);
        builder.define("timeColumn", TokenType.IDENTIFIER);
        builder.define("outputSizeColumn", TokenType.IDENTIFIER);
        builder.define("outputTimeColumn", TokenType.IDENTIFIER);
        return builder.build();
    }

    @Override
    public void initialize(String[] arguments) {
        sizeCol = ((Identifier) arguments.value("sizeColumn")).value();
        timeCol = ((Identifier) strings.value("timeColumn")).value();
        outputSizeCol = ((Identifier) arguments.value("outputSizeColumn")).value();
        outputTimeCol = ((Identifier) strings.value("outputTimeColumn")).value();
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext context) {
        int rowCount = 0;

        for (Row row : rows) {
            Object sizeObj = row.getValue(sizeCol);
            Object timeObj = row.getValue(timeCol);

            if (sizeObj instanceof String && timeObj instanceof String) {
                ByteSize size = new ByteSize((String) sizeObj);
                TimeDuration duration = new TimeDuration((String) timeObj);

                totalBytes += size.getBytes();
                totalNanos += duration.getNanoseconds();
                rowCount++;
            }
        }

        List<Row> result = new ArrayList<>();
        Row aggregate = new Row();
        aggregate.add(outputSizeCol, totalBytes / (1024.0 * 1024.0)); // Convert to MB
        aggregate.add(outputTimeCol, totalNanos / 1_000_000_000.0); // Convert to seconds
        result.add(aggregate);

        return result;
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }

    public void initialize(String[] arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}
