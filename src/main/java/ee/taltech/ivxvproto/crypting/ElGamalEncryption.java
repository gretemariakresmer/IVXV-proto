package ee.taltech.ivxvproto.crypting;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class ElGamalEncryption implements Encryptor {

    private static final String SCHEME = "ElGamal";
    private static final String TRANSFORMATION = "ElGamal/None/NoPadding";
    private static final int KEY_SIZE = 2048;

    private KeyPairGenerator keyPairGenerator;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        keyPairGenerator = KeyPairGenerator.getInstance(SCHEME, "BC");
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();
    }

    @Override
    public String encrypt(String input) throws Exception {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = keyPair.getPublic();

        Cipher cipher = Cipher.getInstance(TRANSFORMATION, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
}
