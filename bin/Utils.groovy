import org.bitcoinj.core.Base58
import org.bitcoinj.core.Sha256Hash
import org.spongycastle.crypto.digests.RIPEMD160Digest
import org.spongycastle.crypto.digests.SHA1Digest
import org.spongycastle.crypto.digests.SHA512Digest

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.security.Key

class Utils {

    static String base58Check(byte[] data) {
        def withCheckSum = addChecksum(data)
        Base58.encode(withCheckSum)
    }

    private static byte[] addChecksum(byte[] input) {
        def inputLength = input.length
        byte[] r = new byte[inputLength + 4];
        System.arraycopy(input, 0, r, 0, inputLength);
        System.arraycopy(Sha256Hash.hashTwice(input), 0, r, inputLength, 4);
        return r;
    }

    /**
     * Encrypts plaintext with AES
     * @param plaintext
     * @param key
     * @return
     */
    public static byte[] AESEncrypt(byte[] plaintext, byte[] key) {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        Key aesKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(plaintext);
    }

    static byte[] concat(byte[]... buffers) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte [] b : buffers) {
            try {
                baos.write(b);
            }
            catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return baos.toByteArray();
    }

    static def sha1(byte[] data) {
        def digest = new SHA1Digest()

        digest.update(data, 0, data.length)
        def result = new byte[digest.digestSize]
        digest.doFinal(result, 0)
        return result.encodeHex()
    }

    static def sha256(byte[] data) {
        return Sha256Hash.of(data).bytes.encodeHex()
    }

    static def sha512(byte[] data) {
        def digest = new SHA512Digest()

        digest.update(data, 0, data.length)
        def result = new byte[digest.digestSize]
        digest.doFinal(result, 0)
        return result.encodeHex()
    }


    static def sha256d(byte[] data) {
        return Sha256Hash.twiceOf(data).bytes.encodeHex()
    }

    static def ripemd160(byte[] data) {
        def digest = new RIPEMD160Digest()

        digest.update(data, 0, data.length)
        def result = new byte[digest.digestSize]
        digest.doFinal(result, 0)
        return result.encodeHex()
    }

    static def sha256hash160(byte[] data) {
        org.bitcoinj.core.Utils.sha256hash160(data).encodeHex()
    }
}