create table target_staging_table1
(
	id bigint auto_increment
		primary key,
	batch_id varchar(255) not null,
	run_date datetime not null,
	case_id varchar(255) null,
	sample_barcode varchar(255) null,
	workflow_schedule_reason varchar(255) null,
	latest_completed_ext_step varchar(255) null,
	latest_completed_step_time varchar(255) null,
	time_current varchar(255) null,
	duration varchar(255) null,
	threshold varchar(255) null,
	delays varchar(255) null
)
;

