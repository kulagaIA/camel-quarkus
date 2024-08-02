package org.apache.camel.quarkus.component.python.deployment;

import io.quarkus.builder.item.MultiBuildItem;

public final class PythonExpressionSourceBuildItem extends MultiBuildItem {

    private final String sourceCode;

    public PythonExpressionSourceBuildItem(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}