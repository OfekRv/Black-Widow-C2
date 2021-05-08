package blackwidow.c2.steganography;

public interface Decoder<E, M> {
    M decode(E encoded);
}
