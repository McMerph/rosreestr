package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;

import java.math.BigDecimal;
import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.format;

public class TotalAreaTableCell extends CenteredTextTableCell<FlatExcerpt, BigDecimal> {

    @Override
    protected String getTextFill(BigDecimal totalArea) {
        return format(totalArea);
    }

    @Override
    protected boolean isDangerStyle(BigDecimal item) {
        return Objects.isNull(item);
    }

}
