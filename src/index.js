import L from 'leaflet';
import KML from 'leaflet-kml';
import 'leaflet/dist/leaflet.css';
import './index.css'
// stupid hack so that leaflet's images work after going through webpack
import marker from 'leaflet/dist/images/marker-icon.png';
import marker2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
    iconRetinaUrl: marker2x,
    iconUrl: marker,
    shadowUrl: markerShadow
});

let map = document.createElement('div');
map.id = "mapid";
document.body.appendChild(map);

var mymap = L.map('mapid').setView([52.17, 21.0], 15);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);

var MARKERS = {};

fetch('//localhost:8080/types')
    .then(res => res.json())
    .then(json => {
        for (var typeOfPlace of json) {
            MARKERS[typeOfPlace.shortname] = {
                iconUrl: typeOfPlace.icon,
                name: typeOfPlace.name,
                id: typeOfPlace.id,
                ordering: typeOfPlace.ordering
            };
        }
    });

function showFeatures(featureCollection) {
    for (const feature of featureCollection) {
        L.geoJson(feature,{

        }).addTo(mymap)
    }
    return undefined;
}

fetch('http://localhost:8080/places')
    .then(res => res.text())
    .then(txt => JSON.parse(txt))
    .then(geoJson => showFeatures(geoJson.features));
