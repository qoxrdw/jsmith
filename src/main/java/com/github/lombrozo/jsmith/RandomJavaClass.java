/*
 * MIT License
 *
 * Copyright (c) 2023-2024 Volodya Lombrozo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.lombrozo.jsmith;

import org.cactoos.io.ResourceOf;

/**
 * Random Java class.
 * @since 0.1
 */
public final class RandomJavaClass {

    private final String parser;
    private final String lexer;

    private final String rule;

    public RandomJavaClass() {
        this(
            "grammars/Java8ReducedParser.g4",
            "grammars/Java8ReducedLexer.g4",
            "compilationUnit"
        );
    }

    /**
     * Constructor.
     * @param parser Parser.
     * @param lexer Lexer.
     * @param rule Rule.
     */
    public RandomJavaClass(
        final String parser,
        final String lexer,
        final String rule
    ) {
        this.parser = parser;
        this.lexer = lexer;
        this.rule = rule;
    }

    /**
     * Source code of the class.
     * @return Source code of the class.
     */
    public String src() {
        return new RandomScript(
            new ResourceOf(this.parser),
            new ResourceOf(this.lexer)
        ).generate(this.rule);
    }
}
