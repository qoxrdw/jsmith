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

import com.github.lombrozo.jsmith.Rand;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * Ebnf suffix ANTLR production.
 * The rule definition:
 * {@code
 * ebnfSuffix
 *     : QUESTION QUESTION?
 *     | STAR QUESTION?
 *     | PLUS QUESTION?
 *     ;
 * }
 * @since 0.1
 */
public final class EbnfSuffix implements RuleDefinition {

    /**
     * Parent rule.
     */
    private final RuleDefinition parent;

    /**
     * Operation.
     */
    private final String operation;

    /**
     * Question.
     */
    private final String question;

    /**
     * Random generator.
     */
    private final Rand rand;

    /**
     * Constructor.
     * @param operation Operation.
     */
    EbnfSuffix(final String operation) {
        this(new Empty(), operation);
    }

    /**
     * Constructor.
     * @param operation Operation.
     * @param question Question.
     */
    EbnfSuffix(final String operation, final String question) {
        this(new Empty(), operation, question);
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param operation Operation.
     */
    public EbnfSuffix(final RuleDefinition parent, final String operation) {
        this(parent, operation, "");
    }

    /**
     * Constructor.
     * @param parent Parent rule.
     * @param operation Operation.
     * @param question Question.
     */
    public EbnfSuffix(final RuleDefinition parent, final String operation, final String question) {
        this.parent = parent;
        this.operation = operation;
        this.question = question;
        this.rand = new Rand();
    }

    @Override
    public RuleDefinition parent() {
        return this.parent;
    }

    @Override
    public String generate() {
        if (Objects.isNull(this.operation)) {
            throw new IllegalArgumentException(
                String.format(
                    "Operation is required for EbnfSuffix %s",
                    this
                )
            );
        }
        return String.format(
            "%s%s",
            this.operation,
            Optional.ofNullable(this.question).orElse("")
        );
    }

    @Override
    public void append(final RuleDefinition rule) {
        throw new UnsupportedOperationException("Unsupported operation yet");
    }

    @Override
    public String toString() {
        return String.format(
            "ebnfSuffix(%s%s)", this.operation, Optional.ofNullable(this.question).orElse("")
        );
    }

    /**
     * Multiply rule based on ebfn suffix.
     * @param from Rule to multiply.
     * @return Multiplied rule.
     */
    RuleDefinition multiplier(RuleDefinition from) {
        final RuleDefinition result;
        switch (this.operation) {
            case "?":
                if (this.rand.flip()) {
                    result = from;
                    break;
                } else {
                    result = new Empty();
                    break;
                }
            case "+": {
                final int number = this.rand.nextInt(5) + 1;
                result = new Several(Collections.nCopies(number, from));
                break;
            }
            case "*": {
                final int number = this.rand.nextInt(5);
                result = new Several(Collections.nCopies(number, from));
                break;
            }
            default:
                throw new IllegalArgumentException(
                    String.format(
                        "Unsupported operation %s for EbnfSuffix %s",
                        this.operation,
                        this
                    )
                );
        }
        return result;
    }
}
