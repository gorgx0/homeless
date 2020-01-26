@Grab('org.orbisgis:h2gis:1.5.0')
@GrabConfig(systemClassLoader=true)


import org.h2gis.functions.factory.H2GISDBFactory

import java.sql.Connection

def base = H2GISDBFactory.createSpatialDataBase("test01")
println base.getClass()

