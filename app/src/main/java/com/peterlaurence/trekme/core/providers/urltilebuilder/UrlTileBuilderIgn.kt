package com.peterlaurence.trekme.core.providers.urltilebuilder


class UrlTileBuilderIgn(private val api: String, private val layer: String) : UrlTileBuilder {
    override fun build(level: Int, row: Int, col: Int): String {
        return "https://wxs.ign.fr/$api/geoportail/wmts?SERVICE=WMTS&VERSION=1.0.0&REQUEST=GetTile&STYLE=normal&LAYER=$layer&EXCEPTIONS=text/xml&Format=image/jpeg&tilematrixset=PM&TileMatrix=$level&TileRow=$row&TileCol=$col&"
    }
}