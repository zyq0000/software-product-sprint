// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public final class FindMeetingQuery {
    public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
        Collection<String> attendeesInRequest = request.getAttendees();
        long requestDuration = request.getDuration();

        //Find the ranges occupied by the attendees in the request
        ArrayList<TimeRange> occupiedRanges = new ArrayList<>();
        for (Event e : events){
            boolean occupiedByAttendee = false;
            Set<String> eAttendees = e.getAttendees();

            for (String attendee : attendeesInRequest) {
                if (eAttendees.contains(attendee)){
                    occupiedByAttendee = true;
                    break;
                }
            }
            if (occupiedByAttendee){
                occupiedRanges.add(e.getWhen());
            }
        }

        //Sort the occupied ranges by the start time
        occupiedRanges.sort(TimeRange.ORDER_BY_START);

        //Find free ranges and remove the overlap
        ArrayList<TimeRange> freeRanges = new ArrayList<>();
        TimeRange previousRange = TimeRange.fromStartDuration(0, 0);
        for(TimeRange currentRange : occupiedRanges) {
            if (currentRange.overlaps(previousRange)){
                // overlap
                // |---|           |---------|
                //    |---|           |---|
                int newPrevStart = previousRange.start();
                int newPrevEnd = Math.max(previousRange.end(), currentRange.end());
                previousRange = TimeRange.fromStartEnd(newPrevStart, newPrevEnd, false);
            }
            else {
                // not overlap
                // |---| |---|
                int freeRangeStart = previousRange.end();
                int freeRangeEnd = currentRange.start();
                previousRange = currentRange;
                if (freeRangeEnd - freeRangeStart < requestDuration) continue;
                freeRanges.add(TimeRange.fromStartEnd(freeRangeStart, freeRangeEnd, false));
            }            
        }

        // Add the range from the end of the last occupied range to the end of the day
        // Also include the situation with no occupied ranges
        if (TimeRange.WHOLE_DAY.end()- previousRange.end() >= requestDuration){
            freeRanges.add(TimeRange.fromStartEnd(previousRange.end(), TimeRange.WHOLE_DAY.end(), false));
        }

        return freeRanges;
    
    }
}
