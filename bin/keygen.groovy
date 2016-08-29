import org.bitcoinj.core.ECKey
import org.bitcoinj.params.MainNetParams
/**
 * generate EC key
 */

def key = ECKey.fromPrivateAndPrecalculatedPublic(
        "6a0e1d6af051d547100ae0b6b63b65ab0212d88541f983e19f911c3b75a79ea5".decodeHex(),
        "036fb4b2146c3b00ed4136d5de11912cee3c6239b3209fc60701c4b306fb57e223".decodeHex()
)

println "privKey as Hex: $key.privateKeyAsHex"
// 6a0e1d6af051d547100ae0b6b63b65ab0212d88541f983e19f911c3b75a79ea5
println "privKey as WIF: ${key.getPrivateKeyAsWiF(MainNetParams.get())}"
// KzmsJuQBASwuB2ZwDKKEnjxTkv7vvHrRdQcPUr8u2X2nQTbZwjC2

println "pubKey as Hex: $key.publicKeyAsHex"
// 036fb4b2146c3b00ed4136d5de11912cee3c6239b3209fc60701c4b306fb57e223