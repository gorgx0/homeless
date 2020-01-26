@Grab('org.orbisgis:h2gis:1.5.0')
@GrabConfig(systemClassLoader=true)

import groovy.sql.Sql
import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import org.h2gis.functions.factory.H2GISDBFactory
import org.h2gis.functions.factory.H2GISFunctions
import org.h2gis.functions.spatial.convert.ST_GeomFromText

import java.sql.Connection
import java.sql.DriverManager


def url = 'jdbc:h2:tcp://localhost:5555/bazada'

def connection = DriverManager.getConnection(url, 'sa', '')
H2GISFunctions.load(connection)
def sql = new Sql(connection)


def parse = new XmlSlurper().parse(new File('../../data/doc.kml'))

places = parse.Document.Folder[0]

int count=0
def each = places.Placemark.each {
    coordinates = it.Point.coordinates.text().trim().split(',')
    punkt = ST_GeomFromText.toGeometry("POINT (${coordinates[0]} ${coordinates[1]})", 4326)
    res = sql.executeInsert('INSERT INTO PLACES (NAME, DESCRIPTION, STYLE, LOCATION) VALUES (?,?,?,?)',[it.name.text() ,it.description.text() ,it.styleUrl.text(), punkt])
    count++
}
println "Inserted ${count} records"
