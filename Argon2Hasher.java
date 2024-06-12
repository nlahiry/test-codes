import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

public class Argon2Hasher {

    private static final int HASH_LENGTH = 256; // Adjusted hash length to 256 bytes
    private static final int PARALLELISM = 1; // Adjust parallelism as needed
    private static final int MEMORY = 65536; // Adjust memory cost in KiB as needed
    private static final int ITERATIONS = 3; // Adjust iterations as needed

    public static String getHashedValue(String value1, String value2, String value3) {
        String dataToHash = String.join(":", value1, value2, value3);
        byte[] hash = generateHash(dataToHash.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    // No salt generation here

    private static byte[] generateHash(byte[] data) {
        Argon2Parameters params = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withParallelism(PARALLELISM)
                .withMemoryAsKiB(MEMORY)
                .withIterations(ITERATIONS)
                .withHashLength(HASH_LENGTH)
                .build();

        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(params);
        byte[] hash = new byte[HASH_LENGTH];
        generator.generateBytes(data, hash);
        return hash;
    }
}
