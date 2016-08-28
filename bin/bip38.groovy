import com.lambdaworks.crypto.SCrypt
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.params.MainNetParams

/**
 * generate encrypted private key(bip38)
 */

def params = MainNetParams.get()

def priv = "6a0e1d6af051d547100ae0b6b63b65ab0212d88541f983e19f911c3b75a79ea5".decodeHex()
def key = ECKey.fromPrivate(priv)

println encryptNoEC(key, "Satoshi", params);

/**
 * Encrypts a key without using EC multiplication.
 *
 * @param key
 * @param passphrase
 * @param params
 * @return
 *
 * @see 'https://github.com/dbasch/BIP38/blob/master/src/main/java/com/fruitcat/bitcoin/BIP38.java'
 */
public static String encryptNoEC(ECKey key, String passphrase, NetworkParameters params) {
    byte[] keyBytes = key.privKeyBytes;
    def address = key.toAddress(params)

    byte[] tmp = address.toString().getBytes("ASCII");
    byte[] hash = Sha256Hash.twiceOf(tmp).bytes;
    byte[] addressHash = Arrays.copyOfRange(hash, 0, 4);
    byte[] scryptKey = SCrypt.scrypt(passphrase.getBytes("UTF8"), addressHash, 16384, 8, 8, 64);
    byte[] derivedHalf1 = Arrays.copyOfRange(scryptKey, 0, 32);
    byte[] derivedHalf2 = Arrays.copyOfRange(scryptKey, 32, 64);

    byte[] k1 = new byte[16];
    byte[] k2 = new byte[16];
    for (int i = 0; i < 16; i++) {
        k1[i] = (byte) (keyBytes[i] ^ derivedHalf1[i]);
        k2[i] = (byte) (keyBytes[i+16] ^ derivedHalf1[i+16]);
    }

    byte[] encryptedHalf1 = Utils.AESEncrypt(k1, derivedHalf2);
    byte[] encryptedHalf2 = Utils.AESEncrypt(k2, derivedHalf2);

    byte[] header = [ 0x01, 0x42, (byte) (key.isCompressed() ? 0xe0 : 0xc0) ] as byte[];
    byte[] encryptedPrivateKey = Utils.concat(header, addressHash, encryptedHalf1, encryptedHalf2);

    return Utils.base58Check(encryptedPrivateKey);
}
