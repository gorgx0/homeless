import L from 'leaflet';
import KML from 'leaflet-kml';
import 'leaflet/dist/leaflet.css';
import './index.css'

let map = document.createElement('div');
map.id = "mapid";
document.body.appendChild(map);

var mymap = L.map('mapid').setView([52.17, 21.0], 15);
console.log(map);
console.log(mymap);
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);

fetch('data/doc.kml')
    .then(res => res.text())
.then(kmltext => {
    const parser = new DOMParser();
    const kml = parser.parseFromString(kmltext, 'text/xml');
    const track = new L.KML(kml);
    mymap.addLayer(track);
    const bounds = track.getBounds();
    mymap.fitBounds(bounds);
});
