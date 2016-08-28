import com.subgraph.orchid.data.Base32

/**
 * provide d
 */


// https://github.com/takahashize/bc-exercise2
def url = 'https://git.io/v65IJ'
def base32url = Base32.base32Encode(url.bytes)
println "bip38passphraseSatoshibase32${base32url}"