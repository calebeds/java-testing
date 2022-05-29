package org.calebe;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;

import java.util.function.Supplier;

import static com.greghaskins.spectrum.Spectrum.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;


@RunWith(Spectrum.class)
public class SpectrumTest {{
    describe("A square", () -> {
        Supplier<Rectangle> theSquare = let(() -> new Rectangle(5,5));
        it("should be square", () -> {
            assertThat(theSquare.get().isSquare()).isTrue();
        });
        it("should have aspect ratio of 1", () -> {
            assertThat(theSquare.get().getAspectRatio()).isCloseTo(1.0, offset(0.01));
        });
        it("should have same sides", () -> {
            assertThat(theSquare.get().getSides()).containsExactly(5, 5);
        });
    });

    describe("A rectangle", () -> {
        it("should not be square", () -> {
            assertThat(new Rectangle(10, 2).isSquare()).isFalse();
        });
    });

}}//ANONYMOUS CONSTRUCTOR?
