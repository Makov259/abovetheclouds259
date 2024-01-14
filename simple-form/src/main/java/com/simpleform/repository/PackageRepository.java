package com.simpleform.repository;

import com.simpleform.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,Long> {
}
