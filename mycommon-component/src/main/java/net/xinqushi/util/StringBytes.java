package net.xinqushi.util;

public class StringBytes {

    public StringBytes() {
    }

    public static String toHexString(byte bytes[]) {
        char buf[] = new char[bytes.length * 2];
        int radix = 16;
        int mask = radix - 1;
        for (int i = 0; i < bytes.length; i++) {
            buf[2 * i] = digits[bytes[i] >>> 4 & mask];
            buf[2 * i + 1] = digits[bytes[i] & mask];
        }

        return new String(buf);
    }

    public static byte[] hexStringToByteArray(String hex) {
        if (hex == null)
            return null;
        String bss[] = hex.split(" ");
        hex = "";
        for (int i = 0; i < bss.length; i++)
            hex = (new StringBuilder(String.valueOf(hex))).append(bss[i]).toString();

        int stringLength = hex.length();
        if (stringLength % 2 != 0)
            throw new IllegalArgumentException("Hex String must have even number of characters!");
        byte result[] = new byte[stringLength / 2];
        int j = 0;
        for (int i = 0; i < result.length; i++) {
            char hi = Character.toLowerCase(hex.charAt(j++));
            char lo = Character.toLowerCase(hex.charAt(j++));
            result[i] = (byte) (Character.digit(hi, 16) << 4 | Character.digit(lo, 16));
        }

        return result;
    }

    public static void main(String args[]) {
        String byteString = "48 54 54 50 2F 31 2E 31 20 32 30 30 20 4F 4B 0D 0A 44 61 74 65 3A 20 57 65 64 2C 20 33 31 20 44 65 63 20 32 30 30 38 20 30 35 3A 35 37 3A 34 34 20 47 4D 54 0D 0A 53 65 72 76 65 72 3A 20 41 70 61 63 68 65 2F 31 2E 33 2E 33 33 20 28 55 6E 69 78 29 20 6D 6F 64 5F 6A 6B 2F 31 2E 32 2E 31 30 0D 0A 43 6F 6E 74 65 6E 74 2D 4C 65 6E 67 74 68 3A 20 31 31 39 37 31 0D 0A 43 6F 6E 6E 65 63 74 69 6F 6E 3A 20 63 6C 6F 73 65 0D 0A 43 6F 6E 74 65 6E 74 2D 54 79 70 65 3A 20 61 70 70 6C 69 63 61 74 69 6F 6E 2F 76 6E 64 2E 77 61 70 2E 6D 6D 73 2D 6D 65 73 73 61 67 65 0D 0A 0D 0A 2E C1 00 01 02 03 04 05 06 07 00 05 02 00 24 55 22 68 74 74 70 3A 2F 2F 64 6D 65 6E 75 2E 35 31 63 6F 6F 6C 62 61 72 2E 63 6F 6D 2F 64 6D 65 6E 75 2F 64 2E 8E 54 01 00 00 01 00 00 44 30 01 30 41 08 BE AB C6 B7 CD C6 BC F6 00 00 02 00 01 49 30 01 49 41 08 D0 D4 B8 A3 BF CE CC C3 1E 31 32 35 39 30 36 38 31 38 37 36 38 38 38 7C 31 30 31 35 39 30 36 36 31 38 37 32 38 38 38 00 03 00 01 49 33 02 42 41 0C CA D6 BB FA C1 E5 C9 F9 C7 E9 D4 B5 20 31 32 35 39 30 38 35 30 30 34 31 31 30 31 36 35 7C 31 30 31 35 39 30 36 36 31 31 37 31 38 38 38 00 04 00 01 44 30 03 30 41 0C D3 EF D2 F4 C7 E9 BB B0 CD C6 BC F6 00 00 05 00 04 49 30 01 49 41 0A C7 E9 B8 D0 B2 E6 C9 D5 B0 FC 1E 31 32 35 39 30 36 38 31 38 38 35 38 38 38 7C 31 30 31 35 39 30 36 36 31 38 37 32 38 38 38 00 06 00 04 49 30 02 49 41 0A B3 AC BC B6 D1 DE D3 F6 B0 C9 21 31 32 35 39 30 38 35 30 30 34 32 31 30 31 36 35 7C 31 30 31 35 39 30 31 31 32 32 38 31 30 31 36 35 00 07 00 04 49 30 03 41 41 08 C3 F7 D0 C7 D4 DA CF DF 20 31 32 35 39 30 38 36 39 30 37 37 31 35 35 30 33 7C 31 30 31 35 39 30 36 36 31 31 37 31 38 38 38 00 08 00 04 49 30 04 49 41 0A B3 AC EC C5 B2 CA C1 E5 B0 F1 20 31 32 35 39 30 38 36 39 30 37 37 31 35 31 30 33 7C 31 30 31 35 39 30 36 36 31 31 37 31 38 38 38 00 09 00 04 49 30 05 30 41 0E D3 E9 C0 D6 B0 CB D8 D4 CB E6 C9 ED CC FD 21 31 32 35 39 30 38 35 30 30 34 36 31 30 31 36 35 7C 31 30 31 35 39 30 31 31 32 32 38 31 30 31 36 35 00 0A 00 04 49 30 06 41 41 0F CA EC C5 AE B1 A1 C9 C0 2A B2 E3 B2 E3 B0 FE 20 31 32 35 39 30 37 31 35 38 31 32 30 31 34 30 7C 31 30 31 35 39 30 31 31 32 32 38 31 30 31 36 35 00 0B 00 04 49 30 07 30 41 08 D0 D4 B8 A3 D3 D0 D4 BC 20 31 32 35 39 30 37 31 35 38 30 32 33 33 36 30 7C 31 30 31 35 39 30 31 31 32 32 38 31 30 31 36 35 00 0C 00 04 49 30 08 30 41 0C B7 E7 C9 A7 CA EC C5 AE D0 C4 BE AD 21 31 32 35 39 30 38 35 30 30 34 33 31 31 32 31 31 7C 31 30 31 35 39 30 31 31 30 31 32 31 31 32 31 31 00 0D 00 04 49 30 09 30 41 0A D2 C6 B6 AF BC CE C4 EA BB AA 1F 31 32 35 39 30 35 37 37 30 34 36 33 30 31 7C 31 30 31 35 39 30 31 31 30 31 32 31 31 32 31 31 00 0E 00 01 44 30 04 30 41 0C B3 AC EC C5 C1 E5 C9 F9 CD C6 BC F6 00 00 0F 00 0E 49 30 01 49 41 0C D7 EE D0 C2 C1 E5 C9 F9 C5 C5 D0 D0 20 31 32 35 39 30 38 35 30 30 34 31 31 32 31 31 7C 31 30 31 35 39 30 31 31 30 39 35 31 31 32 31 31 00 10 00 0E 49 30 02 42 41 0E C8 C8 C0 B1 C1 C3 C8 CB C1 E5 D2 F4 BF E2 20 31 32 35 39 30 38 36 39 30 37 37 31 35 31 31 36 7C 31 30 31 35 39 30 36 36 31 31 37 31 38 38 38 00 11 00 0E 49 30 03 42 41 0B BC A4 C7 E9";
        byte bytes[] = hexStringToByteArray(byteString);
        String s = new String(bytes);
        System.out.println(s);
    }

    static final char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

}
