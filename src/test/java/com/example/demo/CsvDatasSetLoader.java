package com.example.demo;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.springframework.core.io.Resource;

public class CsvDatasSetLoader extends AbstractDataSetLoader
{
    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception
    {
        return new CsvDataSet(resource.getFile());
    }
}