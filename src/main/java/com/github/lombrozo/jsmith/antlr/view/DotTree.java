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
package com.github.lombrozo.jsmith.antlr.view;

import com.github.lombrozo.jsmith.antlr.rules.Root;
import com.github.lombrozo.jsmith.antlr.rules.Rule;
import java.util.List;

public final class DotTree implements Text {

    /**
     * Origin text.
     */
    private final Text origin;

    /**
     * Constructor.
     * @param origin Origin text.
     */
    public DotTree(final Text origin) {
        this.origin = origin;
    }

    @Override
    public Rule writer() {
        return this.origin.writer();
    }

    @Override
    public List<Text> children() {
        return this.origin.children();
    }

    @Override
    public String output() {
        final StringBuilder builder = new StringBuilder();
        builder.append("digraph JsmithGenerativeTree{\n");
        this.travers(new TextLeaf(new Root(), "root"), this.origin, builder);
        builder.append("}");
        return builder.toString();
    }


    private void travers(final Text parent, final Text current, final StringBuilder builder) {
        builder.append(
            String.format(
                "\"%s\" -> \"%s\";\n",
                parent.writer().name(),
                current.writer().name()
            )
        );
        for (final Text child : current.children()) {
            this.travers(current, child, builder);
        }
    }
}
