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
package com.github.lombrozo.jsmith.antlr.rules;

/**
 * Literal rule.
 * The ANTLR grammar definition:
 * WARNING: This is NOT a part of the ANTLR grammar!
 * @since 0.1
 */
public final class Literal implements RuleDefinition {

    /**
     * Parent rule.
     */
    private final RuleDefinition parent;

    /**
     * Text of the literal.
     */
    private final String text;

    /**
     * Constructor.
     * @param text Text of the literal.
     */
    public Literal(final String text) {
        this(new Empty(), text);
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param text Text of the literal.
     */
    public Literal(final RuleDefinition parent, final String text) {
        this.parent = parent;
        this.text = text;
    }

    @Override
    public RuleDefinition parent() {
        return this.parent;
    }

    @Override
    public String generate() {
        return this.text;
    }

    @Override
    public void append(final RuleDefinition rule) {
        throw new UnsupportedOperationException("Literal cannot have children yet");
    }
}
