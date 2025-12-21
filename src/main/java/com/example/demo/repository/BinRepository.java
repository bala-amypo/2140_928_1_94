@Override
public Bin update(Long id, Bin updatedBin) {
    Bin existing = getById(id);

    existing.setIdentifier(updatedBin.getIdentifier());
    existing.setLocationDescription(updatedBin.getLocationDescription());
    existing.setLatitude(updatedBin.getLatitude());
    existing.setLongitude(updatedBin.getLongitude());
    existing.setCapacityLiters(updatedBin.getCapacityLiters());
    existing.setActive(updatedBin.getActive());

    if (updatedBin.getZone() != null) {
        Zone zone = zoneRepository.findById(updatedBin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        existing.setZone(zone);
    }

    return binRepository.save(existing);
}
