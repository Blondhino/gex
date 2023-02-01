package com.ebiondic.testing.decoder

import com.ebiondic.common.decoder.StringDecoder
import javax.inject.Inject

class FakeStringDecoder @Inject constructor() : StringDecoder {
  override fun decodeString(encodedString: String): String = encodedString
}