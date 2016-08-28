import org.bitcoinj.crypto.BIP38PrivateKey
import org.bitcoinj.params.MainNetParams

/**
 * decrypt bip38 private key and sign(d)
 */

def d = "bip38passphraseSatoshibase32nb2hi4dthixs6z3joqxgs3zpoy3dkskk"
def bip38 = '6PYLbAfbftqVrDupqAyjajbNLKNSZvXTpfuRBe668HjFyM6m7zSNUfjBDN'
def bip38key = BIP38PrivateKey.fromBase58(MainNetParams.get(), bip38)
def eckey = bip38key.decrypt("Satoshi")
println eckey.privateKeyAsHex

def signed = eckey.signMessage(d)
println signed