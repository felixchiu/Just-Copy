use reporting;

drop table if exists reporting.lv_ops_horizon3_labalerts_workflow;

create table reporting.lv_ops_horizon3_labalerts_workflow
(
  batch_id varchar(255) null,
  run_date datetime null,
  CASE_ID         int         null,
  BLOOD           varchar(50) null,
  SAMPLE_CREATEDT datetime    null,
  MATRIXBARCODE   varchar(52) null,
  MATRIXRACK      varchar(59) null,
  EXTRACTION      varchar(54) null,
  NGS             varchar(54) null,
  MLPA            varchar(52) null,
  FRAX            varchar(52) null,
  CONFIRMATION    varchar(52) null,
  ASURAGEN        varchar(52) null,
  MANUAL          varchar(52) null
);

drop table if exists reporting.lv_ops_horizon3_labalerts_currentstep;

create table reporting.lv_ops_horizon3_labalerts_currentstep
(
  batch_id varchar(255) null,
  run_date datetime null,
  BLOOD                 varchar(50)     null,
  WORKFLOW              varchar(50)     null,
  RNK                   int             null,
  SAMPLE_ID             varchar(50)     null,
  CREATED               datetime        null,
  SAMPLE_CREATED_PST    datetime        null,
  MODDT                 datetime        null,
  DESTSAMPLEID          varchar(50)     null,
  CREATEDTIME           datetime        null,
  MODDT_TIME            datetime        null,
  PLATE                 varchar(50)     null,
  LOCKED                varchar(20)     null,
  WORKFLOWSTEP          varchar(55)     null,
  STEP                  varchar(50)     null,
  WORKFLOW_CREATED      datetime        null,
  WORKFLOW_MODDT        datetime        null,
  WORKFLOW_MODDT_PST    datetime        null,
  WORKFLOW_STATUS       varchar(50)     null,
  WORKFLOW_ID           int             null,
  SOURCE_SAMPLEID       varchar(50)     null,
  SOURCE_BARCODE        varchar(50)     null,
  SOURCE_CRERATEDT      datetime        null,
  WORKFLOWID            int             null,
  OUTPUTSAMPLEID        varchar(50)     null,
  SAMPLEBARCODE         varchar(50)     null,
  EXTRACTIONDATE        datetime        null,
  REARRAY_SAMPLEID      varchar(50)     null,
  REARRAY_MATRIXBARCODE int             null,
  REARRAY_COLLECTIONDT  datetime        null,
  REARRAY_VOLUME        decimal(28, 10) null,
  REARRAY_RACK_BARCODE  varchar(50)     null,
  REARRAY_ITEMLABEL     varchar(50)     null,
  REARRAY_LOCKED        varchar(50)     null
);

drop table if exists reporting.lv_ops_horizon3_lv_labalert_new;

create table reporting.lv_ops_horizon3_lv_labalert_new
(
  batch_id varchar(255) null,
  run_date datetime null,
  Case_id            int          null,
  BLOOD              varchar(50)  null,
  SAMPLEID           varchar(50)  null,
  PROCESS_TYPE       varchar(50)  null,
  PROCESS_STATUS     varchar(50)  null,
  PROCESS_MODDT      datetime     null,
  IOTYPE             varchar(50)  null,
  PROCESS_STARTED    datetime     null,
  PROCESS_END        datetime     null,
  PREV_PLATE         varchar(120) null,
  PREV_STEP          varchar(120) null,
  SOURCESAMPLEID     varchar(20)  null,
  DESTSAMPLEID       varchar(20)  null,
  CURRENT_STEP_PLATE varchar(120) null,
  CURRENT_STEP       varchar(120) null,
  WORKFLOW_NAME      varchar(50)  null,
  RNK                int          null,
  MATRIXRACK         varchar(150) null,
  EXTRACTION         varchar(150) null,
  EXTRACTION_status  varchar(150) null,
  NGS                varchar(150) null,
  MLPA               varchar(150) null,
  FRAX               varchar(150) null,
  CONFIRMATION       varchar(150) null,
  ASURAGEN           varchar(150) null,
  MANUAL             varchar(150) null
);

