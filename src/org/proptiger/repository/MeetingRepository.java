package org.proptiger.repository;

import java.util.Date;
import java.util.List;

import org.proptiger.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
	public List<Meeting> findByMeetingDate(Date meetingDate);
}
