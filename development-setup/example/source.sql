select case_id, sample_barcode, workflow_schedule_reason, latest_completed_ext_step, latest_completed_step_time, time_current, duration, threshold, delays
from source_staging_table1 where delays = '4'
